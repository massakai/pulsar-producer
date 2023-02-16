package com.github.massakai.pulsar.producer.message.provider;

public interface MessageProvider<T> {

    /**
     * 次のメッセージを返します。
     *
     * @return 次のメッセージ
     * @throws java.util.NoSuchElementException メッセージがない場合
     */
    T next();

    /**
     * さらに要素がある場合にtrueを返します。
     *
     * @return さらに要素がある場合はtrue
     */
    boolean hasNext();
}
