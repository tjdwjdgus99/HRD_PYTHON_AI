# 위험 감지 시스템 (hazard detection system)

❓ Problem : 묻지마 칼부림 증가로 사회적 불안감이 올라가고 있다.

‼ Idea : 길거리에 칼을 들고 다니는 사람을 미리 감지하면 좋지 않을까??

💯 Solution : 영상, 이미치 머신러닝을 통해서 카메라, 사진를 통해 칼을 감지해보자!!


## 영상 : [https://www.youtube.com/watch?v=tnDuCUsiYcI]


## 주요 기능과 로직

- **카메라** : 동작중 3~5가지의 객체 정보를 가져옴
- **영상 분류** : 카메라에서 칼 객체와 사람 두 명이 감지 시 긴급 전화창으로 이동함
- **이미지 분류** : 칼 이미지를 칼과 칼이 아닐 확률을 구분하여 출력해줌
- **긴급 전화** : 112,119에 곧바로 전화할 수 있고 기본 전화 앱으로 이동하여 원하는 전화번호로 전화할 수 있도록 함

## 메인 로직 1 : 메뉴
1. 기본화면에서 아래에서 위로 스크롤

| 메뉴 | 내용|
|--|--|
|추론 시간|실시간 추론에 걸리는 시간|
|정확도|일정 정확도를 두어 객체 감지 조정|
|최대 출력 갯수|화면에 출력 할 수 있는 객체 최대 갯수|
|연산|GPU, CPU를 선택할 수 있음|
|모델|실시간 연산에 사용할 모델 선택|
|이미지 분류 CNN모델|이미지 분류에 필요한 CNN모델|
|긴급 전화|긴급 전화창으로 수동 이동|

## 메인 로직 2 : 긴급 전화

1. 112 : 112로 곧바로 전화연결
2. 119 : 119로 곧바로 전화연결
3. 다이얼 : 기본 전화 앱으로 연결되어 직접 다이얼을 입력함

## 구조도
![image](https://github.com/tjdwjdgus99/HRD_PYTHON_AI/assets/78959224/d4656ee5-a47d-4b86-a56e-d874d2511381)

## UML
![image](https://github.com/tjdwjdgus99/HRD_PYTHON_AI/assets/78959224/874bc65f-6622-4044-9dfd-501b62bb017e)

## 기술 스택
- JAVA(Android Studio)
- Python(Jupyther notebook)


## 개발 기간

- 2021.7.27 ~ 8.31  (5주)
    

## 기본 베이스 참고 자료 : [https://github.com/tensorflow/examples/blob/master/lite/examples/object_detection/android]

![DB](https://user-images.githubusercontent.com/77563814/133954614-b1a28410-baac-4f6b-a1e0-3c35b5d5d93b.png)
