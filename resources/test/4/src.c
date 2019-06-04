#include "stdio.h"

struct A {
	int f,g,q,num;
}a;

struct B {
	int f,g,w;
	struct A *getA;
}b;



int main() {
	int s1 = a.f + a.g + a.q;
	int s2 = b.f + b.g + b.w;
	int s3 = b.getA->f;
	int s4 = b.getA->g + b.getA->num;

	return 0;
}
