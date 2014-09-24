#include <stdio.h>

int main()
{
    int a=0;
    int b=0;

    printf("두 피연산자를 입력하세요 : ");
    scanf("%d %d", &a, &b);
    printf("두수의 합은 %d입니다.\n", a+b);
    printf("두수의 차은 %d입니다.\n", a-b);
    printf("두수의 곱은 %d입니다.\n", a*b);
    printf("두수의 나눈값은 %g입니다.\n", a/(float)b);

    return 0;
}


