class Solution:
    def isValid(self, s: str) -> bool:
        stack = []

        for c in s:
            if c == '(' or c == '[' or c == '{':
                stack.append(c)
            else:
                if not stack:
                    return False
                
                if c == ')':
                    if stack[len(stack)-1] == '(':
                        stack.pop()
                    else :
                        return False
                elif c == ']':
                    if stack[len(stack)-1] == '[':
                        stack.pop()
                    else :
                        return False
                else:
                    if stack[len(stack)-1] == '{':
                        stack.pop()
                    else :
                        return False
            
        return not stack