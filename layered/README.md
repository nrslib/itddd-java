# 『ドメイン駆動設計入門　ボトムアップでわかるドメイン駆動設計の基本』サンプルプロジェクト in Java

レイヤードアーキテクチャバージョンです。

## 補足

ドメイン層のオブジェクトを DTO に詰め替えていますが、これは任意です。  
プロジェクトの方針により、アプリケーションサービスの戻り値にもできます。  
プレゼンテーション層などでドメインオブジェクトのメソッドを呼び出さない共通の認識を作るとよいでしょう。  

---

## 操作

`{}` は適宜置き換えてください。  

### ユーザ

#### ユーザ作成
```shell
curl --location --request POST 'localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "test-name"
}'
```

#### ユーザ一覧
```shell
curl --location --request GET 'localhost:8080/api/users'
```

#### ユーザ詳細
```shell
curl --location --request GET 'localhost:8080/api/users/{user-id}'
```

#### ユーザ更新
```shell
curl --location --request PUT 'localhost:8080/api/users/{user-id}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "test-name-changed"
}'
```

#### ユーザアップグレード
```shell
curl --location --request POST 'localhost:8080/api/users/{user-id}/upgrade' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "test-name"
}'
```

#### ユーザ削除
```shell
curl --location --request DELETE 'localhost:8080/api/users/{user-id}'
```

### サークル

#### サークル作成
```shell
curl --location --request POST 'localhost:8080/api/circles' \
--header 'Content-Type: application/json' \
--data-raw '{
    "circleName" : "test-circle-name",
    "ownerId" : "{user-id}"
}'
```

#### サークル取得
```shell
curl --location --request GET 'localhost:8080/api/circles/{circle-id}'
```

#### サークル候補者一覧
```shell
curl --location --request GET 'localhost:8080/api/circles/{circle-id}/candidates?page={page}&size={size}'
```
