def solution(progresses, speeds):
    days = []
    
    for progress, speed in zip(progresses, speeds):
        remainings = 100 - progress
        day = (remainings + speed -1) // speed
        days.append(day)
        
    answer = []
    count = 0
    now_day = days[0]
    
    for day in days :
        if day <= now_day :
            count += 1
        else :
            answer.append(count)
            now_day = day
            count = 1
    answer.append(count)
    return answer