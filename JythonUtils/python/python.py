def Print_int():
    a=3
    return a
def Print_string(str):
    s=str+"fht"
    return s

def Print_list():
    y=[1,2,3,4,5,6,7]
    return y


def Print_List(l):
    print(l)
    l.append(0.5)
    l.append(0.8)
    return l

def Print_Map(m1):
    print(m1)
    m1['weibo']=5
    print(m1)
    print(type(m1))
    return m1
def Print_Map2(m2):
    print(m2)
    li = []
    for key  in m2.items():
        li.append(key)
    print(li)
    return li

def Print_map():
    dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'}
    return dict
def Print_double(d):
    print(d)
    d=d+0.254
    return d

def Print_boolean(b):
    print(b)
    b=False
    return b
