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
};

int main() {
	A a;
	B b;
	int t = a.f() + a.g() + a.q() + b.f() + b.g() + b.w();
}
