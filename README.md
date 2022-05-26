# RaidGame
襲撃防衛イベント用のプラグイン

## コマンド
- `/raidgame setbase` : 現在の自分の場所で襲撃の開始地点を設定
- `/raidgame start <teamName>` : 指定したチームでゲームを開始
- `/raidgame stop` : 現在進行中のゲームを停止
- `/raidgame createteam <teamName>` : 任意の名前でチームを作成
- `/raidgame deleteteam <teamName>` : チームを削除
- `/raidgame showteams` : 作成したチーム一覧を表示
- `/raidgame addmember <teamName> <player>` : チームにプレイヤーを追加
- `/raidgame removemember <teamName> <member>` : チームからメンバーを削除
- `/raidgame showmembers <teamName>` : チームのメンバー一覧を表示
- `/raidgame showresult <teamName>` : 指定したチームのリザルトを表示
- `/raidgame showtotalresult` : 全てのチームのリザルトとランキングを表示
- `/raidgame result` : 上のコマンドの表示内容をオンラインのプレイヤー全員に表示

## ゲームの流れ
1. まずは襲撃の開始地点を設定します。ゲーム開始時にゲームを行うプレイヤーがここにTPされます。
2. 次にチームを作成して、メンバーを追加します。メンバーのプレイヤーの画面にはチームメンバー一覧のスコアボードが表示されます。
3. 作成したチームの中からゲームを行うチームを指定して、ゲームを開始します。
4. 襲撃が終了してゲームが終わるとリザルトが表示されます。
5. 全てのチームのゲームが終わったら、コマンドでトータルの結果をオンラインプレイヤー全員に表示します。
6. なお、ゲームの途中でコマンドでゲームを止めてやり直したり、もう一度ゲームをやり直したりすることもできます。その場合、前回のリザルトは上書きされます。

## 備考
襲撃の開始地点を設定してからでないとゲームを開始することはできません
