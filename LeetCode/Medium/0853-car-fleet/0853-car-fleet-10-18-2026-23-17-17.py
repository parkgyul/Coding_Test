class Solution:
    def carFleet(self, target: int, position: list[int], speed: list[int]) -> int:

        # 시작점이 도착점으로부터 가까운 순으로 정렬
        cars = sorted(
            zip(position, speed),
            reverse = True
        )

        fleets = 0
        last_time = 0

        for pos, spd in cars:
            time = (target-pos)/spd

            if time > last_time:
                last_time = time
                fleets += 1
        

        return fleets