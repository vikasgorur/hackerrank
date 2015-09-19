import itertools
import sys
import uuid


def pairs():
    for line in sys.stdin:
        yield [int(x) for x in line.strip().split()]


class Country:
    def __init__(self, count=1):
        # number of astronauts from this country
        self.id = uuid.uuid1()
        self.count = count

    def incr(self):
        self.count += 1
        return self

    def __repr__(self):
        return "Country<count: {0}>".format(self.count)

    @classmethod
    def same(cls, c1, c2):
        "Mark c1 and c2 as the same country"
        c1.id = c2.id = uuid.uuid1()


def main():
    n, i = [int(x) for x in sys.stdin.readline().strip().split()]

    countries = []
    astronauts = {}

    for a, b in pairs():
        a_seen, b_seen = a in astronauts, b in astronauts
        if a_seen and not b_seen:
            astronauts[b] = astronauts[a].incr()
        elif not a_seen and b_seen:
            astronauts[a] = astronauts[b].incr()
        elif not a_seen and not b_seen:
            c = Country()
            astronauts[a] = c
            astronauts[b] = c.incr()
            countries.append(c)
        elif a_seen and b_seen:
            Country.same(astronauts[a], astronauts[b])

    uniq_countries = itertools.groupby(countries, key=lambda c: c.id)
    country_counts = [sum([c.count for c in cs]) for (id, cs) in uniq_countries]
    singletons = [1] * (n - len(astronauts.keys()))
    print(list(country_counts))
    country_pairs = itertools.combinations(country_counts + singletons, 2)
    #print(list(country_pairs))
    print(sum([c1*c2 for (c1, c2) in country_pairs]))


if __name__ == "__main__":
    main()
