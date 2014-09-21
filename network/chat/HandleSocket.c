#include <stdio.h>      /* for printf() and fprintf() */
#include <string.h>
#include <sys/socket.h> /* for recv() and send() */
#include <unistd.h>     /* for close() */
#include <stdio_ext.h>

#define BUFSIZE 32   /* Size of receive buffer */

void DieWithError(char *errorMessage);  /* Error handling function */

void InputMessage(int sock) {
    char msgBuf[BUFSIZE];
    int msgLen=0;

    memset(msgBuf, 0, BUFSIZE);

    printf("# ");

    // 만약 문자열의 길이가 버퍼보다 크면 그 다음번에 보냄
    fgets(msgBuf, BUFSIZE, stdin);

    // 입력한 문자열 길이
    msgLen = strlen(msgBuf);

    if(msgLen < 1) {
        return;
    }

    // 종료 명령어 체크
    if(!strcmp(msgBuf, "/quit\n")) {
        DieWithError("Quit!");
    }

    printf("\n");

    // 입력한 문자열 길이 전송 (4 byte)
    send(sock, &msgLen, 4, 0);
    // 입력한 문자열 전송
    send(sock, msgBuf, msgLen, 0);
}

int HandlePacket(char* remoteAddr, char *buf, int* len) {
    char msgBuf[BUFSIZE+1];
    int msgLen = 0;

    memset(msgBuf, 0, BUFSIZE+1);

    if(*len < 4) return 0;

    // 실제 메시지 사이즈 구해옴
    msgLen = (int)*buf;
    if(msgLen > *len - 4) return 0;

    memcpy(msgBuf, buf+4, msgLen);

    // 남은 길이 체크
    *len = *len - msgLen - 4;
    if(*len > 0) { // 패킷이 붙어온 경우 처리
        memcpy(buf, buf+4+msgLen, *len);
    }

    printf("%s > %s\n", remoteAddr, msgBuf);

    return 1;
}

void HandleSocket(int sock, char* remoteAddr) {
    char recvBuf[BUFSIZE+4];
    int recvLen=0;
    int result=-1;

    memset(recvBuf, 0, BUFSIZE+4);

    while(1) {
        if(BUFSIZE+4-recvLen < 1) {
            break;
        }

        result = recv(sock, recvBuf + recvLen, BUFSIZE+4-recvLen, 0);
        if(result < 1) {
            break;
        }

        // 지금까지 받은 패킷 길이 체크
        recvLen += result;

        // 패킷 처리
        while(1) {
            if(!HandlePacket(remoteAddr, recvBuf, &recvLen)) {
                break;
            }
            InputMessage(sock);
        }
    }

    close(sock);
}
