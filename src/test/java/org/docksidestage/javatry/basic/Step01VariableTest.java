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

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8:mai(x) -> mystic8null:mai(o)
        // 定義を見ていいのを忘れていた
        // 定義をざっと見ると、おおよそ `.toString()` で文字列に変換される
        // `log` では `null` は null という文字列が出力される
        // #1on1: String以外のクラスをStringと+すると、Stringに引き摺り込まれる (2026/07/27)
        // そのとき、toString()が呼ばれる。
        // ↑log()の中でtoString()が呼ばれると思っていた by いしやまさん
        // ↑log()の前に+の処理は終わっているので、このエクササイズとは直接は関係ないけど、log()まで見てるのは素晴らしい。
        // toString()は、Object型で宣言されていて、すべてのクラスが継承している。
        // #1on1: "null" という文字列になるのは、プログラミング言語の決め (2026/07/27) 
        // Pythonだとどうだっけ？ → ぐぐると、エラーになるっぽい。
        // C#だと、空文字になる。
        // それぞれ、メリデメ。
        // 安全性ではエラーになるほうがいい。
        // 多少楽に実装するって面では素通りがいい。
        // "null" だと、本番で画面にnullって出てきやすい、一方で開発時は不具合がわかりやすい。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic"; // 1(mystic)
        String land = "oneman"; // 2(oneman)
        sea = land;
        land = land + "'s dreams"; // 3("'s dreams"), 4(oneman's dreams)
        log(sea); // your answer? => oneman's dreams(x) -> oneman(o)
        // コードをちゃんと読んでいなかった
        // 確かに IDEA の変数の表示が違うのもあって気づくべきだった
        // #1on1: インスタンスとは？ (2026/07/27)
        // クラスについて具体的に...メモリ上に置いたもの by いしやまさん
        // 一軒家の例。
        // BigDecimalのエクササイズにもつなげてみた。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94); // 1(94)
        BigDecimal land = new BigDecimal(415); // 2(415)
        sea = land;
        sea = land.add(new BigDecimal(1)); // 3(1), 4(416)
        sea.add(new BigDecimal(1)); // 5(1), 6(417)
        log(sea); // your answer? => 416(o)

        // #1on1: インスタンスエクササイズ (2026/07/27)
        // 416は2(415)
        // add()は、自分自身を変えて、それを戻すものと思った!? by いしやまさん
        // add()のJavaDoc見てみた。add()のソースコードリーディングしてみた。
        // #1on1: immutableとは？ (2026/07/27)
        // Javaだと自作immutableが基本。(recordという新しい文法も少しあるけど汎用的ではない)
        // immutableのクラスのメリデメ:
        // o メリット: 変更箇所を追いやすい、可読性。from 安全性。
        // o デメリット: 若干設計が複雑になりやすい印象、インスタンス多いのでメモリ。
        // immutableの歴史(Java):
        // コンピューターの都合から人間の都合に。
        // Javaの場合、過去の経緯もあるし、コンセプトもあってか、バランス主義な印象。
        // 一方で、100%immutable推しの文化もある。

        // TODO done ishiyama [読み物課題] 応援してる "A" にもデメリットはあるよ by jflute (2026/07/27)
        // https://jflute.hatenadiary.jp/entry/20181008/yourademerit
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => (x) -> null(o)
        // 言い訳すると真面目にオブジェクト指向の言語を書くのが久しぶりで細かいルールを本当に覚えていない
        // 初期化せずに変数宣言するとデフォルトコンストラクタが呼ばれるかと思ったがそれは C++ だった (By ChatGPT)
        // Java では初期化せずに変数宣言すると null になるらしい
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0(o)
        // これはわかる
        // プリミティブな型だから初期値が決まっていて int は 0
        // ただし、ローカル変数の場合は未代入の変数の利用がコンパイルエラー (By ChatGPT)
        // Swift も未初期化の変数は利用不可 (By ChatGPT)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null(o)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1|null|burn(x) -> bigband|1|null|magician
        // オブジェクト変数を渡すのでオブジェクトの持っている値が上書きされると考えてしまった。
        // ChatGPT に見てもらったところ、ローカル変数が指すオブジェクト自体を上書きしているため、実引数が指すオブジェクトには影響されない
        // #1on1: 少なくともJavaでは、変数そのものが引数で渡されることはない (2026/07/27)
        // 必ず、呼び出し側で中身を取り出して(参照)、それを引数変数に代入して渡す。
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor(o)
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416(o)
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor(o)
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        log(sea, land, piari);
    }

    int piari;
    // インスタンス変数という呼び名に親しみがなく、一瞬わからなかった
    // フィールド、もっと言えばインスタンスフィールドという言い方もできるらしい (By ChatGPT)
    // クラス変数という言い方も割と自分は使っていたが、スタティック変数に対して使うべきらしい(By ChatGPT)
    // #1on1: インスタンス変数 == インスタンスフィールド にありー== メンバー変数 (2026/07/27)
    // 文法用語も大事だけど、文法用語が流行らないケースもある。

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * boolean / double / char によりそれぞれ false / 0.25 / 'a' を初期値として宣言し、
     * ラッパー型に代入して log() でカンマ区切りの文字列で表示
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
        boolean flag = false;
        double d = 0.25;
        char c = 'a';

        Boolean flagWrapper = flag;
        Double dWrapper = d;
        Character cWrapper = c;
        log(flagWrapper, dWrapper, cWrapper);
    }
}
