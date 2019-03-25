#include <iostream>
using namespace std;

void f(int x) {
  int t;
  if (   x >   0) {
    t =   1;
  }else{
    t=2;
  }
}

void g(int x) {
  int t;
  if (x > 0) {
    t = 1;
  } else {
    t = 3;
  }
}
int main(int argc, char *argv[]) {

  f(1);
  g(2);
  return 0;
}