# KotlinJsonConverter

## 動作確認環境
- JDK: amazon-corretto-11

## 実行方法

### ビルド

```shell
./gradlew build
```

### 実行

```shell
java -jar build/libs/kotlin_json_converter-0.0.1-SNAPSHOT.jar JSONファイルURLorパス LOCAL/REMOTE
```
指定するオプションの意味は以下の通り。
- JSONファイルURLorパス: JSONファイルの場所を指定
- LOCAL/REMOTE: 上記JSONファイルの置き場所がローカルの場合はLOCAL、サーバー上の場合はREMOTEを指定