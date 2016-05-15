from random import randint

f = open('hello-world.txt', 'r+')

print f.read()

f.write('Hello data-science!\n' + str(randint(0, 100)) + '\n') # to write to file
