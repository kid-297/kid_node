package top.kid297.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/7/3 15:11
 */
@Slf4j
public class ImmuntableExample1 {

    private static final String str = "c";
    private static final Map<Integer,Integer> map = Maps.newHashMap();
    private static final ImmutableList<Integer> list = ImmutableList.of(1,2,3);
    private static final ImmutableSet set = ImmutableSet.copyOf(list);
    private static final ImmutableMap<Integer, Integer> integerImmutableMap1 = ImmutableMap.of(1,2,3,4);
    private static final ImmutableMap<Integer, Integer> integerImmutableMap2 = ImmutableMap.<Integer,Integer>builder().put(1,2).build();

    public static void main(String[] args) {
        Collections.unmodifiableMap(map);

    }

}
