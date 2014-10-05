#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), connect(), send(), and recv() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_addr() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <sys/stat.h>
#include "file.h"

#define MAXPENDING 5    /* Maximum outstanding connection requests */
#define RCVBUFSIZE 1024   /* Size of receive buffer */

void DieWithError(char *errorMessage);  /* Error handling function */
void HandleSocket(int sock, void *buf, int size);

int main(int argc, char *argv[])
{
    int servSock;                    /* Socket descriptor for server */
    int clntSock;                    /* Socket descriptor for client */
    struct sockaddr_in echoServAddr; /* Local address */
    struct sockaddr_in echoClntAddr; /* Client address */
    unsigned short echoServPort;     /* Server port */
    unsigned int clntLen;            /* Length of client address data structure */
    char buf[RCVBUFSIZE];
    struct send_file sendFile;
    struct ready_transaction readyTransaction;
    int recvLen = 0;
    struct stat file_stat;
    int result = 0;
    FILE *fp;

    if (argc != 2) {   /* Test for correct number of arguments */
        fprintf(stderr, "Usage:  %s <Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);  /* First arg:  local port */

    /* Create socket for incoming connections */
    if ((servSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");

    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));   /* Zero out structure */
    echoServAddr.sin_family = AF_INET;                /* Internet address family */
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    echoServAddr.sin_port = htons(echoServPort);      /* Local port */

    /* Bind to the local address */
    if (bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed");

    /* Mark the socket so it will listen for incoming connections */
    if (listen(servSock, MAXPENDING) < 0)
        DieWithError("listen() failed");


    for (;;) /* Run forever */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(echoClntAddr);

        /* Wait for a client to connect */
        if ((clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, 
                               &clntLen)) < 0)
            DieWithError("accept() failed");

        /* clntSock is connected to a client! */

        printf("Handling client %s\n", inet_ntoa(echoClntAddr.sin_addr));

        // 파일 전송 요청을 확인
        HandleSocket(clntSock, &sendFile, sizeof(sendFile));
        printf("Recv SendFile Packet\n");

        // 이어 받기를 위해 클라이언트에게 offset 알림
        if(!stat(sendFile.file_name, &file_stat)) {
            readyTransaction.offset = file_stat.st_size;
        } else {
            readyTransaction.offset = 0;
        }

        // 받아야할 파일 크기 확인
        sendFile.file_size -= readyTransaction.offset;

        printf("%s Open\n", sendFile.file_name);
        fp=fopen(sendFile.file_name, "ab");
        // 파일의 Mode와 uid, gid 유지
        chmod(sendFile.file_name, sendFile.mode);
        chown(sendFile.file_name, sendFile.uid, sendFile.gid);

        // 처리 준비 완료
        send(clntSock, &readyTransaction, sizeof(readyTransaction), 0);
        printf("Ready Transaction\n");

        // 서버에 이미 같은 크기 파일이 있을때는 처리 안함
        if(sendFile.file_size > 0) {
            while((result = recv(clntSock, buf, RCVBUFSIZE, 0))>0) {
                printf("Recv Data - %d\n", result);
                fwrite(buf, sizeof(char), result, fp);
                sendFile.file_size -= result;
                if(sendFile.file_size<1) {
                    break;
                }
            }
        }

        close(clntSock);
        fclose(fp);

        printf("End Transaction\n");
    }
}

