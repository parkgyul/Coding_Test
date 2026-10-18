import heapq # 최소힙

def solution(k, score):
    heap = []
    answer = []
    
    heapq.heappush(heap, score[0])
    answer.append(score[0])
    for i in range(1, len(score)):
        if len(heap) < k:
            heapq.heappush(heap, score[i])
            answer.append(heap[0])
        else:
            if(heap[0] < score[i]):
                heapq.heappop(heap)
                heapq.heappush(heap, score[i])
            answer.append(heap[0])
            
    return answer



