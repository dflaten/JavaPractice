# HashMaps
Hashmaps allow you to store Key Value pairs and can be very useful for many
problems. 

## When should I use them?
* If you require repeated fast access to data during the execution of an
algorithm.
* You need to store the relationship between two sets of data in order to
  compute a result. This is achieved through the mechansim of a key-value pair. 

## Real World Applications
* Telecommunications: Implement a phone book with the name of the person as the
  key, and their number as a value. 
* E-commerce: Search for details of a product using its product ID as the key. 
* File system: When a user interacts with a file syste, they see the file name
  and path. System uses a hashmap to store the correspondence between the file
  name and its path. 

## Problems
### Two Sum
Given an array of integers and a target integer, return the indices of the
the numbers that add up to the target. 

There is exactly one solution in the array and you can't use the same element
twice.

#### Solution
First the n^2 solution: 

```java
    public int[] twoSum(int[] nums, int target) {
        //easy way, iterate through it twice
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                   // Do Nothing 
                } else if ((nums[i] + nums[j]) == target) {
                    return new int[]{i,j};
                }
            }
        }    
        return new int[0];
```

Now the smarter solution: 
```java
   public int[] twoSum(int[] nums, int target) {
        // Key - value we are looking for compared to current element.    
        HashMap<Integer, Integer> valueIndexMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int otherValue = target - nums[i];
            if (valueIndexMap.containsKey(otherValue)) {
                return new int[]{i, valueIndexMap.get(otherValue)};
            } else {
                valueIndexMap.put(nums[i], i);
            }
        }
        //Never used
        return new int[0];
    }
```

