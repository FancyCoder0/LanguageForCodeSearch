#include<cstdio>

class A {
public:
	int f() {
		return 0;
	}
	int g() {
		return 0;
	}
	int q() {
		return 0;
	}

	int num;
};

class B {
public:
	int f() {
		return 0;
	}
	int g() {
		return 0;
	}
	int w() {
		return 0;
	}
	A getA() {
	    static A t;
	    t.num = 10;
	    return t;
	}
};



int main() {
	A a;
	B b;
	int s1 = a.f() + a.g() + a.q();
	int s2 = b.f() + b.g() + b.w();
	int s3 = b.getA().f();
	int s4 = b.getA().g() + b.getA().num;

	return 0;
}
