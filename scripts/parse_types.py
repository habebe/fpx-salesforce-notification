import json
import types
import sys
import dateutil.parser

f = file(sys.argv[1],"r")
data = f.read().strip()
dataset = json.loads(data)
records = dataset["records"]
record = records[0]


for i in record:
    name = i
    s = name[0].lower()
    name = s + name[1:]
    value = record[i]
    t = type(value)    
    if t == types.IntType:
        t = "int"
    elif t == types.UnicodeType:
        t = "String"
    elif t == types.BooleanType:
        t = "boolean"
    elif t == types.FloatType:
        t = "double"
    else:
        t = "String"
    if type(value) != types.DictType:
        print '@JsonProperty(value="{0}")'.format(i)
        print "private",t,name+";"

a = json.dumps(record)
a = a.replace('"',"\\\"")
#print "String jsonString = "'"' + a + '";'
#print '"'+str(record)+'"'


