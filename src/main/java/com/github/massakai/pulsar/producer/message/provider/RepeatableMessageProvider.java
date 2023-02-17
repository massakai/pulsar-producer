package com.github.massakai.pulsar.producer.message.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class RepeatableMessageProvider<T> implements MessageProvider<T> {
    private final List<T> list;
    private final AtomicInteger index = new AtomicInteger();

    public RepeatableMessageProvider(final Collection<T> collection) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException("collection must contain elements.");
        }
        // 外部でcollectionが更新される可能性はあるので、ArrayListに詰め直しておく
        list = new ArrayList<>(collection);
    }

    @Override
    public T next() {
        int i = index.getAndUpdate(v -> v < list.size() - 1 ? v + 1 : 0);
        if (i >= list.size()) {
            throw new NoSuchElementException();
        }
        return list.get(i);
    }

    @Override
    public boolean hasNext() {
        return true;
    }
}
