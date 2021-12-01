import site  # required!
import numpy as np


def compute_total(*nums):
    return float(np.array(nums).sum())
