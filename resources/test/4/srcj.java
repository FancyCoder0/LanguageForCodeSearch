public class test {
    public class A {
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
    }
    public class B {
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
            return new A();
        }
    }

    int main() {
        A a = new A();
        B b = new B();
        int s1 = a.f() + a.g() + a.q();
        int s2 = b.f() + b.g() + b.w();
        int s3 = b.getA().f();
        int s4 = b.getA().g() + b.getA().num;

        return 0;
    }
}
