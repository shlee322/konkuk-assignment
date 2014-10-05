#include <stdio.h>      /* for printf() and fprintf() */
#include <string.h>
#include <sys/socket.h> /* for recv() and send() */
#include <unistd.h>     /* for close() */
#include <stdio_ext.h>

void DieWithError(char *errorMessage);  /* Error handling function */

void HandleSocket(int sock, void *buf, int size) {
    char recvBuf[1024];
    int recvLen = 0;
    int result=-1;

    while(1) {
        if(size-recvLen < 1) {
            break;
        }

        result = recv(sock, recvBuf+recvLen, size-recvLen, 0);
        if(result < 1) {
            DieWithError("Error recv()");
            break;
        }

        // 지금까지 받은 패킷 길이 체크
        recvLen += result;

        if(size <= recvLen) {
            memcpy(buf, recvBuf, size);
            break;
        }
    }
}
