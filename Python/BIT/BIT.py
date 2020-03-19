class BinaryIndexedTree:
    def __init__(self, array):
        n_size = len(array) + 1
        self._tree = [0] * n_size
        for i in range(1, n_size):
            self.update(i, n_size, array[i - 1])

    def update(self, index, size, value):
        while index < size:
            self._tree[index] += value
            index += index & -index  # Least relevant binary number

    def query(self, left, right):
        return self._get_sum(right) - self._get_sum(left - 1)

    def _get_sum(self, right):
        result = 0

        while right > 0:
            result += self._tree[right]
            right -= right & -right  # Least relevant binary number

        return result

    def __str__(self):
        return str(self._tree[1:])
