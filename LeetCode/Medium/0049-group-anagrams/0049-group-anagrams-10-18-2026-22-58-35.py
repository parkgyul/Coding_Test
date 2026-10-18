from  collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
        groups = defaultdict(list)

        for str in strs:
            key = "".join(sorted(str))

            groups[key].append(str)

        return list(groups.values())

        