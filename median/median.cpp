#include <set>
#include <iostream>
#include <stdio.h>

class Solution {

private: 

    std::multiset<unsigned long long> left;
    std::multiset<unsigned long long> right;    

    bool not_found;

public:
    // The invariant after a rebalance is:
    // left.size() == right.size() || left.size() == right.size() + 1

    void Rebalance() {
        if (left.size() < right.size()) {
            std::multiset<unsigned long long>::iterator right_head;
            unsigned long long head_val;

            right_head = right.begin();
            head_val   = *right_head;

            right.erase(right_head);
            left.insert(head_val);
        }

        if (left.size() == right.size() + 2) {
            std::multiset<unsigned long long>::iterator left_last;
            unsigned long long last_val;

            left_last = --left.end();
            last_val  = *left_last;

            left.erase(left_last);
            right.insert(last_val);
        }
    }

    void AddNumber(unsigned long long x) {
        if (left.empty() || x <= *(left.rbegin())) {
            left.insert(x);
        } else {
            right.insert(x);
        }

        not_found = false;
        Rebalance();
    }

    void RemoveNumber(unsigned long long x) {
        std::multiset<unsigned long long>::iterator iter_l, iter_r;

        if ((iter_l = left.find(x)) != left.end()) {
            left.erase(iter_l); not_found = false;

        } else if ((iter_r = right.find(x)) != right.end()) {
            right.erase(iter_r); not_found = false;
        } else {
            not_found = true;
        }

        Rebalance();
    }

    void PrintMedian() {
        if (not_found || left.empty()) {
            printf("Wrong!\n");
        } else if (left.size() == right.size()) {
            unsigned long long left_last  = *left.rbegin();
            unsigned long long right_head = *right.begin();

            unsigned long long median = (left_last + right_head) / 2;

            if ((left_last + right_head) % 2 == 0) {
                printf("%lld\n", median);
            } else {
                printf("%lld.5\n", median);
            }
        } else {
            printf("%lld\n", *left.rbegin());
        }
    }

    void run() {
        unsigned long long n;
        std::cin >> n;

        std::string s;
        unsigned long long x;

        for (unsigned int i = 0; i < n; i++) {
            std::cin >> s >> x;
            if (s == "a") {
                AddNumber(x);
            } else if (s == "r") {
                RemoveNumber(x);
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
