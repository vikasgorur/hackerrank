import sys

def lex_next(w):
    splits = ((w[0:i], w[i:]) for i in range(len(w)-1, 0, -1))

    for (s, t) in splits:
        greater_chars = [c for c in t if c > s[-1]]
        if len(greater_chars) > 0:
            replacement = min(greater_chars)
            t_list = list(t)
            t_list[t.find(replacement)] = s[-1]

            return s[:-1] + replacement + "".join(sorted(t_list))

    return "no answer"

def main():
    sys.stdin.readline() # number of words, discard
    for word in sys.stdin.readlines():
        print(lex_next(word.strip()))

if __name__ == '__main__':
    main()
