package com.jingyu.common.core.domain;

import com.jingyu.common.utils.StringUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class Cascader<T> {

    private String value;
    private String label;
    private boolean isNode;
    private List<Cascader<T>> children;

    private T raw;

    public Cascader(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public Cascader(String value, String label, boolean isNode) {
        this.value = value;
        this.label = label;
        this.isNode = isNode;
    }

    public void addChildrenNode(Cascader<T> cascader) {
        if(children == null) {
            children = new ArrayList<>();
        }
        children.add(cascader);
    }
    public static <T> Cascader<T> parseCascader(List<String> values, String trueValue, T raw) {
        if(values == null || values.size() < 1) {
            return null;
        }
        if(StringUtils.isEmpty(trueValue)) {
            trueValue = values.get(values.size() - 1);
        }
        String value = values.get(0);
        Cascader<T> cascader =  new Cascader<>(value, value);
        Cascader<T> temp = cascader;
        for (int i = 1; i < values.size(); i++) {
            String val = values.get(i);
            Cascader<T> t = new Cascader<>(val, val);
            if(i == values.size() - 1) {
                t.setNode(true);
                t.setValue(trueValue);
                t.setRaw(raw);
            }
            List<Cascader<T>> tempList = new ArrayList<>();
            tempList.add(t);
            temp.setChildren(tempList);
            temp = t;
        }
        return cascader;
    }

    public void addChildren(List<String> values, String trueValue, T raw) {
        if(this.getChildren() == null) {
            this.setChildren(new ArrayList<>());
        }
        if(StringUtils.isEmpty(trueValue)) {
            trueValue = values.get(values.size() - 1);
        }
        List<String> finalValues = values;
        List<Cascader<T>> list = this.getChildren().stream()
                .filter(item -> item.getValue().equals(finalValues.get(0))).collect(Collectors.toList());
        if(list.size() < 1) {
            Cascader<T> cascader = Cascader.parseCascader(values, trueValue, raw);
            this.addChildrenNode(cascader);
        } else {
            if(values.size() == 2) {
                Cascader<T> cascader = new Cascader<>(trueValue, values.get(1), true);
                cascader.setRaw(raw);
                list.get(0).addChildrenNode(cascader);
            } else {
                values = values.subList(1, values.size());
                list.get(0).addChildren(values, trueValue, raw);
            }
        }
    }
}
