def solution(arr):
    for i, value in enumerate(arr):
        if value >= 50 and value % 2 == 0:
            arr[i] = value // 2
        elif value < 50 and value % 2 == 1:
            arr[i] = value*2
    return arr