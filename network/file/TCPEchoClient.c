#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), connect(), send(), and recv() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_addr() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <sys/stat.h>
#include "file.h"

#define RCVBUFSIZE 1024   /* Size of receive buffer */

void DieWithError(char *errorMessage);  /* Error handling function */
void HandleSocket(int sock, void *buf, int size);

int main(int argc, char *argv[]) {
    int sock;                        /* Socket descriptor */
    struct sockaddr_in echoServAddr; /* Echo server address */
    unsigned short echoServPort;     /* Echo server port */
    char *servIP;                    /* Server IP address (dotted quad) */
    char *fileName;                /* String to send to echo server */
    struct stat file_stat;
    struct send_file sendFile;
    struct ready_transaction readyTransaction;
    FILE *fp;
    char buf[RCVBUFSIZE];
    int result;
    int recvLen = 0;

    if ((argc < 3) || (argc > 4)){    /* Test for correct number of arguments */
        fprintf(stderr, "Usage: %s <Server IP> <File Name> [<Echo Port>]\n", argv[0]);
        exit(1);
    }

    servIP = argv[1];             /* First arg: server IP address (dotted quad) */
    fileName = argv[2];         /* Second arg: string to echo */

    if (argc == 4)
        echoServPort = atoi(argv[3]); /* Use given port, if any */
    else
        echoServPort = 7;  /* 7 is the well-known port for the echo service */

    // 파일 정보를 읽어옴
    if(stat(fileName, &file_stat)) {
        DieWithError("file access failed");
    }

    printf("Load File\n");
    strcpy(sendFile.file_name, fileName); // 파일 이름
    sendFile.file_size = file_stat.st_size; // 파일 사이즈
    sendFile.mode = file_stat.st_mode; // 파일 퍼미션
    sendFile.uid = file_stat.st_uid; // 유저 소유권
    sendFile.gid = file_stat.st_gid; // 그룹 소유권

    /* Create a reliable, stream socket using TCP */
    if ((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");

    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));     /* Zero out structure */
    echoServAddr.sin_family      = AF_INET;             /* Internet address family */
    echoServAddr.sin_addr.s_addr = inet_addr(servIP);   /* Server IP address */
    echoServAddr.sin_port        = htons(echoServPort); /* Server port */
    /* Establish the connection to the echo server */
    if (connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("connect() failed");

    fp=fopen(fileName, "rb");
    if(!fp) {
        DieWithError("file open failed");
    }

    // 파일 전송을 알림
    send(sock, &sendFile, sizeof(sendFile), 0);
    printf("SendFileInfo\n");

    // 서버에서 파일 전송 준비 완료 확인
    HandleSocket(sock, &readyTransaction, sizeof(readyTransaction));
    printf("Ready Transaction - File Offset : %d\n", readyTransaction.offset);

    // 파일 offset 셋팅
    if(fseek(fp, readyTransaction.offset, SEEK_SET)) {
        DieWithError("set offset failed");
    }

    printf("Send!\n");
    while((result = fread(buf, sizeof(char), RCVBUFSIZE, fp))>0) {
        printf("Send - %d\n", result);
        send(sock, buf, result, 0);
    }
    printf("End Send\n");

    //수신 완료까지 대기 (버퍼 재활용)
    while(1) {
        if(recv(sock, buf, RCVBUFSIZE, 0)<1) {
            break;
        }
    }
    printf("End Transaction\n");
    close(sock);
    fclose(fp);
    exit(0);
}
