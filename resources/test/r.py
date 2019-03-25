import sys
import os

r = sys.argv[1]

os.system('srcml %s > %s' % (r + '.cpp', r + '.xml'))


