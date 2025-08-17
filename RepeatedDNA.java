class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for(int i=0;i <=s.length()-10;i++) {
            String p = s.substring(i,i+10);
            if(set.contains(p) && !result.contains(p)) {
                result.add(p);
            } else {
                set.add(p);
            }
        }
        return result;
    }
}
