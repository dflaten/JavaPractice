# Merge Intervals

## Overview
The merge intervals pattern deals with problems involving overlapping
intervals. Here an interval would be something like `[10, 20]` which would
represent all of the seconds between 10 and 20. An overlapping interval would
be `[15, 35]` since there is some overlap between the two. 

You can use this pattern if: 
* The input data is an array of intervals.
* The problem requires dealing with overlapping intervals, either to find their
  intersection, union, or gaps between them. This could be the final result or
  a step in the middle of the computation of intervals. 
* The result doesn't require the intervals to be in a specific order.
* The input list of intervals is not sorted, if it was we would use another
  approach. 

## Real World Examples
* **Display a busy Schedule**: Display the busy hours of a user to other users
  without revealing the individual meetings on a calendar. 

* **Schedule a new meeting**: Add a meeting to the tenative meeting schedule of
  a user in such a way that no two meetings overlap. 

* **Task scheduling in operating systems**: Schedule tasks for the OS based on
  priority and the free slots in the machine's processing schedule. 

## Examples

### Merge Intervals

#### Description
Given an array of closed intervals (closed as in inclusive), where each
interval has a start and end time. The input array is sorted with respect to
the start times of each interval. For example: intervals = [[1,4], [3,6],
[7,9]]. is sorted in terms of 1, 3, and 7. 

Your task is to merge the overlapping intervals and return a new output array
consisting of only the non-overlapping intervals. 

Constraints: 
* 1<= intervals.length <= 10^4
* intervals[i].length = 2
* 0 <= start time <= end time <= 10^4

#### Solution
* Insert the first interval from the input list into the output list. 
* Iterate throught the input (skipping the first) if the current list overlaps
  with the last list in the last merge them and replace the last interval in
  the output list with this one. Otherwise at the interval to the list. 
* Return the output after finishing iterating

```java
  public static List <Interval> mergeIntervals(List <Interval> intervals) {
    // Exceptional case
    if (intervals.size() == 1) {
      return intervals;
    }
    List<Interval> output = new ArrayList<Interval>();
    output.add(intervals.get(0));
    for (int i = 1; i < intervals.size(); i++) {
      int latestOutput = output.size() - 1;
      Interval lastInterval = output.get(latestOutput);
      Interval current = intervals.get(i);
      // Merge If overlapping
      if (lastInterval.getEnd() >= current.getStart()) {
        int mergedEnd = 0;
        if (lastInterval.getEnd() > current.getEnd()) {
          mergedEnd = lastInterval.getEnd();
        } else {
          mergedEnd = current.getEnd();
        }
        Interval mergedInterval = new Interval(lastInterval.getStart(), mergedEnd);
        output.set(latestOutput, mergedInterval);
      // Else add the current Interval to the output
      } else {
        output.add(current);
      }
    }
    return output;
  }
```

