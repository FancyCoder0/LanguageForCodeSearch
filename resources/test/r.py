import sys
import os

s = sys.argv[1].strip()
suffix = 'cpp'
if len(sys.argv) == 3:
    suffix = sys.argv[2].strip()

os.system('docker cp test:/home/%s.%s ~/LanguageForCodeSearch/resources/test' % (s, suffix))
os.system('docker cp test:/home/%s.xml ~/LanguageForCodeSearch/resources/test' % (s))




