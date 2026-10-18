class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        seen = {}
        for i, num in enumerate(nums):
            needed = target - num

            if needed in seen:
                return [seen[needed], i]

            seen[num] = i            
        