#include <set>
#include <iostream>

#include <stdio.h>

class Solution {

private: 

    std::multiset<signed long> numbers;

    bool not_found;

public:
    void AddNumber(signed long x) {
        numbers.insert(x);
        not_found = false;
    }

    void RemoveNumber(signed long x) {
        std::multiset<signed long>::iterator iter;

        if ((iter = numbers.find(x)) != numbers.end()) {
            numbers.erase(iter);
            not_found = false;
        } else {
            not_found = true;
        }
    }

    void PrintMedian() {
        if (numbers.empty() || not_found == true) {
            printf("Wrong!\n");
        } else {
            size_t size = numbers.size();
            std::multiset<signed long>::iterator iter;

            int i = 0;
            iter = numbers.begin();

            while (i != size / 2) {
                i++; iter++;
            }

            if (size % 2 == 0) {
                signed long current = *iter;
                signed long prev    = *(--iter);

                signed long median = (current + prev) / 2;

                if ((current + prev) % 2 == 0) {
                    printf("%ld\n", median);
                } else {
                    printf("%ld.5\n", median);
                }
            } else {
                printf("%ld\n", *iter);
            }
        }
    }

    void run() {
        int n;
        std::cin >> n;

        std::string s[n];
        signed long x[n];

        for (int i = 0; i < n; i++) {
            std::cin >> s[i] >> x[i];
            if (s[i] == "a") {
                AddNumber(x[i]);
            } else if (s[i] == "r") {
                RemoveNumber(x[i]);
            }

            PrintMedian();
        }
    }
};


int main(int argc, char **argv)
{
    Solution solution;
    solution.run();
}
