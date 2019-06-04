import sys
import os

file = sys.argv[1]

dir = os.path.dirname(file)
fname = os.path.basename(file)

os.system('docker cp %s 904adfc95ee7:/home/ft' % file)

os.system('docker exec -ti test sh -c "srcml /home/ft/%s > /home/ft/%s.xml"' % (fname, fname))

os.system('docker cp 904adfc95ee7:/home/ft/%s.xml %s' % (fname, dir))
