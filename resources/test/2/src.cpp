#include<iostream>

void open() {
}

void close() {
}

void open(int x) {
}

void close(int x) {
}

void f() {
    int a = 1;
    if (a > 0) {
        int x = 1;
        open();
        int y1 = 2;
        int y2 = 2;
        close();
        int z = 3;
    } else if (a > 1) {
        int x = 1;
        open();
        int y = 2;
        close();
        int z = 3;
        open();
        close();
    } else if (a > 2) {
        int x = 1;
        close();
        int q = 1;
        open();
        int z = 3;
    } else if (a > 3) {
        int tmp = 1;
        open(tmp);
        int tmp2 = 2;
        close(tmp);
    } else {
        int tmp = 1;
        open(tmp);
        int tmp2 = 2;
        close(tmp2);
    }

}
int main() {

}
