import sys

def lex_next(w):
    splits = ((w[0:i], w[i:]) for i in range(len(w)-1, 0, -1))

    for (s, t) in splits:
        sub = min([c for c in t if c > s[-1]], default=False)
        if not sub: continue
        return s[:-1] + sub + "".join(sorted(t.replace(sub, s[-1], 1)))

    return "no answer"

def main():
    sys.stdin.readline() # number of words, discard
    for word in sys.stdin.readlines():
        print(lex_next(word.strip()))

if __name__ == '__main__':
    main()
