import site  # required!
import numpy as np
import pandas as pd

def compute_total_with_numpy(*nums):
    return float(np.array(nums).sum())

def compute_total_with_pandas(*nums):
    return float(pd.DataFrame(nums).sum())

def compute_total_with_pure_python(*nums):
    return sum(nums)
