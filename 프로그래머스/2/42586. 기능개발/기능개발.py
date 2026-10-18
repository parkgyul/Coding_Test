
def solution(progresses, speeds):
    days = []
    
    for progress, speed in zip(progresses, speeds):
        remaining = 100 - progress
        day = (remaining + speed -1) // speed
        days.append(day)
    
    answer = []
    current_day = days[0]
    count = 0
    
    for day in days:
        if day <= current_day:
            count += 1
        else :
            answer.append(count)
            current_day = day
            count = 1
    answer.append(count)
    return answer