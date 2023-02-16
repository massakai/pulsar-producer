package com.github.massakai.pulsar.producer.message.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleMessageProvider<T> implements MessageProvider<T> {

    private final List<T> list;
    private final AtomicInteger index = new AtomicInteger();

    public SimpleMessageProvider(final Collection<T> collection) {
        // 外部でcollectionが更新される可能性はあるので、ArrayListに詰め直しておく
        list = new ArrayList<>(collection);
    }

    @Override
    public T next() {
        int i = index.getAndIncrement();
        if (i >= list.size()) {
            throw new NoSuchElementException();
        }
        return list.get(i);
    }

    @Override
    public boolean hasNext() {
        return index.get() < list.size();
    }
}
