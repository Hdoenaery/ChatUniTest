import os
java_home = os.environ.get('JAVA_HOME')
print(java_home)
if 'java-11' in java_home:
    print("yes")
