def solution(array):
    max_value = max(array)
    index = array.index(max_value)
    return [max_value, index]