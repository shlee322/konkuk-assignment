#include <stdio.h>

int main()
{
    int a = 0;
    int b = 0;
    int c = 0;

    printf("오늘 하루 용돈을 입력하세요 : ");
    scanf("%d", &a);

    printf("교통비를 입력하세요 : ");
    scanf("%d", &b);

    printf("식비를 입력하세요 : ");
    scanf("%d", &c);

    printf("남은 용돈은 %d원입니다.\n", a - b - c);

    return 0;
}
