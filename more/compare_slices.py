

global L,H,MP


tipos = lambda T,M,t,m,s: (1/3.0)* (T+M)/float(MP)* ( (1 - ((t+m)/2.0*L)) +  (1- (s/H)) + (s/H)*((t+m)/2.0*L)*(float(min(T,M))/max(T,M)))

def analyse(pizza):
    T = 0
    M = 0
    t = 0
    m = 0
    s=0
    for bpart in pizza:
        for part in bpart:
            if part == "T":
                T+=1
            else:
                M+=1
    if T<L:
        t = L - T
    else:
        t = 0
    if M<L:
        m = L-M
    else:
        m = 0
    if H<(T+M):
        s = float((T+M) - H)
    else:
        s = 0
                                  
    print T,M,t,m,s
    return T,M,t,m,s



inputfile = open(raw_input("Type the input:"))
readinputfile = inputfile.read()

lines = readinputfile.splitlines()
print lines

L = int(lines[0].split(" ")[0])
H = int(lines[0].split(" ")[1])
MP = int(lines[0].split(" ")[2])

pizza1 = list()
pizza2 = list()

i=0
while True:
    line = lines[i+1]
    print i
    if line == "$": break
    pizza1.append(list(line.split(" ")))
    i+=1

i+=1
while True:
    line = lines[i+1]
    if line == "$": break
    pizza2.append( list(line.split(" ")))
    i+=1

print pizza1
print pizza2
T,M,t,m,s = analyse(pizza1)
print "First pizza: {} ".format( tipos(T,M,t,m,s))
T,M,t,m,s = analyse(pizza2)
print "Second pizza: {}".format(tipos(T,M,t,m,s))
