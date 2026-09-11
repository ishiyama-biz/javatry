/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author kazuki ishiyama
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over (o)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething()); // 読む必要なし
        runnableSomething(); // 読む必要なし
        log(sea); // your answer? => mysmys (o)
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);
        if (!land) { // true
            sea = sea + mutable.getStageName().length(); // 904 + 6
        }
        log(sea); // your answer? => 910 (o)
        String a = null;
        log(a);
    }

    // String(=null).length() ってなんぼになるんだろう → NullPointerException
    // そりゃそうか
    // だったらやっぱり + 演算子とかの時に `null` とかに勝手に変換しないで欲しいと思ってしまう
    // String a = null;
    // log(a);
    // これなんで IllegalArgumentException が投げられないんだ？
    // 可変長引数だからか (Claude と相談)
    // #1on1: log()メソッドを追ってみた (2026/09/11)

    // #1on1: $オブジェクト指向を最初にやったのがC++ (2026/09/11)
    // step6で深掘りしていきましょう。

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport); // 何もしない
        for (int i = 0; i < 100; i++) { // 100 回インクリメント
            goToPark();
        }
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100 (o)
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea);
        }
        // BBB
    }

    // write methods here
    // #1on1: いいね、メソッドの定義位置が、public側の呼び出し順序と一致して、直感的に把握しやすい (2026/09/11)
    // $メソッドの定義位置、普段も気になってる。Repositoryとかだと、メソッドが大量で順序に迷う、どこに追加しよう。
    // $内容ごとにセクション分けがされているので、自分でセクション分けしている。
    // 呼び出し順序というのは一例ではあって、何かしらの他のルールもあるかもしれないけど...
    // jfluteは呼び出し順序に合わせるスタイルを多くやっている。
    // LastaFluteのActionRequestProcessorをコードを見ながら参考に。
    // 階層構造も入れつつ呼び出し順序を意識しているケース。
    //
    // 一番下に追加される問題。
    // 他人の作ったクラスにメソッド追加、おじゃまします感。
    // 既存クラスの「コード体裁デザイン」を把握して尊重して、その上で修正をして欲しい。
    // 既存クラスに対する責任って、みんな持ってる。おじゃまします感してる場合ではない。
    // そのクラスの「コード体裁デザイン」の責任を、その瞬間持っている。

    // #1on1: いいね、引数名、ニュアンスが入ってて素晴らしい。strでも80点くらいだけど、本気がやるならこれ (2026/09/11)
    // 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す
    private String replaceAwithB(String mayContainsAString) {
        return mayContainsAString.replace("A", "B");
    }

    // 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す
    private String replaceCwithB(String mayContainsCString) {
        return mayContainsCString.replace("C", "B");
    }

    // #1on1: いいね、第一引数、第二引数ともにわかりやすい。役割を書いてる。 (2026/09/11)
    // 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す
    private String quote(String quoted, String quotation) {
        return quotation + quoted + quotation;
    }

    // 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)
    private boolean availableLogging = true;

    private boolean isAvailableLogging() {
        return availableLogging;
    }

    // 一つのString引数、戻り値なし、引数をlog()で表示する
    private void showSea(String sea) {
        log(sea);
    }
}
