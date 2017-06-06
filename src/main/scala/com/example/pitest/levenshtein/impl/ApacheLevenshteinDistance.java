package com.example.pitest.levenshtein.impl;


import com.example.pitest.levenshtein.LevenshteinDistance;

public class ApacheLevenshteinDistance extends LevenshteinDistance {

    public int unlimitedCompare(String left, String right) {
        if(left != null && right != null) {
            int n = left.length();
            int m = right.length();
            if(n == 0) {
                return m;
            } else if(m == 0) {
                return n;
            } else {
                if(n > m) {
                    String p = left;
                    left = right;
                    right = p;
                    n = m;
                    m = p.length();
                }

                int[] var11 = new int[n + 1];

                int i;
                for(i = 1; i <= n; var11[i] = i++) {
                    ;
                }

                for(int j = 1; j <= m; ++j) {
                    int upperLeft = var11[0];
                    char rightJ = right.charAt(j - 1);
                    var11[0] = j;

                    for(i = 1; i <= n; ++i) {
                        int upper = var11[i];
                        int cost = left.charAt(i - 1) == rightJ?0:1;
                        var11[i] = Math.min(Math.min(var11[i - 1] + 1, var11[i] + 1), upperLeft + cost);
                        upperLeft = upper;
                    }
                }

                return var11[n];
            }
        } else {
            throw new IllegalArgumentException("Strings must not be null");
        }
    }

}
