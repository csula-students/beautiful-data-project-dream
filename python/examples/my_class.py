class MyClass: # can defined inheritance MyClass(ParentClass, ParentClass2)
    """A simple example class"""
    i = 12345

    def __init__(self):
        print 'no arg constructor is called'

    def f(self): # first argument is always self
        return 'hello world'
    
myclass = MyClass()
print myclass.f()
