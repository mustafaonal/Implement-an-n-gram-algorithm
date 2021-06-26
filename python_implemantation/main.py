import re  # check if a string contains the specified search pattern
from operator import itemgetter  # used for ordering n-grams highest to lowest in list
import timeit


def read_file(input_file):  # file read function, convert all letters lower and eliminate punctuation marks
    f = open(input_file, 'r')
    content = f.read()
    content = content.lower()
    content = re.sub(r'[^A-Za-zçğöşüıâî1-9\n ]', '', content)  # from a to z, turkish letters ,numbers and
    content = content.split()                                 # end of line only acceptable
    return content


def find_n_grams(input_list, n_gram):
    ngrams = []
    temp = []
    for i in range(len(input_list)-(n_gram-1)):
        seq = ' '.join(input_list[i:i+n_gram])
        if seq in temp:
            ngrams[temp.index(seq)][1] = ngrams[temp.index(seq)][1] + 1
        else:
            ngrams.append([seq, 1])
            temp.append(seq)
    ngrams.sort(key=itemgetter(1), reverse=True)
    print("Top 100 items in ", n_gram, "-gram")
    for j in range(100):
        print(j+1, ":", ngrams[j][0], " - ", ngrams[j][1])
    print("total number of ", n_gram, "-grams: ", len(ngrams))


# read file function calls for each text file separately and assign a list
text = read_file('BİLİM İŞ BAŞINDA.txt') + read_file('DEĞİŞİM.txt') + read_file('BOZKIRDA.txt') + \
       read_file('DENEMELER.txt') + read_file('UNUTULMUŞ DİYARLAR.txt')


start = timeit.default_timer()
find_n_grams(text, 1)
stop1 = timeit.default_timer()
find_n_grams(text, 2)
stop2 = timeit.default_timer()
find_n_grams(text, 3)
stop3 = timeit.default_timer()
print('Time for 1-gram: ', stop1 - start)
print('Time for 2-gram: ', stop2 - stop1)
print('Time for 3-gram: ', stop3 - stop2)
print("Total runtime: ", stop3 - start)
print("Total word count: ", len(text))
