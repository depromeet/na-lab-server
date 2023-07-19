# Na Lab <img src="https://github.com/depromeet/na-lab-server/assets/71487608/7975a91f-83e2-4dd3-88f0-e5d9d429cd8f" align=left width=100>

> 동료의 익명 피드백을 통한 나의 커리어 브랜딩, Na Lab &nbsp;&nbsp; • <b>백엔드</b> 레포지토리



[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=depromeet_na-lab-server&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=depromeet_na-lab-server)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=depromeet_na-lab-server&metric=coverage)](https://sonarcloud.io/summary/new_code?id=depromeet_na-lab-server)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=depromeet_na-lab-server&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=depromeet_na-lab-server)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=depromeet_na-lab-server&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=depromeet_na-lab-server)

[![e2e test](https://github.com/depromeet/na-lab-server/actions/workflows/e2e.yml/badge.svg)](https://github.com/depromeet/na-lab-server/actions/workflows/e2e.yml)

<br/>

### 🧐 Na Lab ?

**오직 나만을 위한 커리어 연구실, Na Lab 🧬🧪**  
‘Na Lab’은 동료의 익명 피드백을 통해 나의 직무 강점을 발견하는 서비스입니다.

### 👉 [Na Lab 바로가기](https://www.nalab.me/)

--- 

</br>


![질문폼만들기1](https://github.com/oyeon-kwon/personal_color/assets/61301574/e5acce5d-a9b9-4200-bc3c-7e6846b89702)
![질문폼만들기2](https://github.com/depromeet/na-lab-client/assets/26461307/02ec7d6a-4a82-4d0a-9c5a-1eaa0de7d166)
> 나의 커리어 브랜딩을 완성해주는 기본질문을 통해 손쉽게 질문폼을 만들 수 있어요    
> 새로운 질문을 추가하고 싶다면 객관식, 주관식으로 자유롭게 질문을 만들어보세요!

<br />

<br />

![질문폼답변하기1](https://github.com/oyeon-kwon/personal_color/assets/61301574/16b0b65b-69ef-4915-a7be-c5ea9b1335ba)
![질문폼답변하기2](https://github.com/depromeet/na-lab-client/assets/26461307/53664ba1-0970-4054-9b49-8071f0a4bf6c)
> 부담스러웠던 동료 평가의 경험을 마치 친구와 심리테스트 하듯 즐겁게 할 수 있도록 설계했어요     
> 나랩의 연구를 책임지는 Dr. 왓슨 박사님과 함께 채팅으로 대화하며 익명으로 피드백을 남길 수 있어요

<br />

<br />


![결과페이지1](https://github.com/oyeon-kwon/personal_color/assets/61301574/f6ae2893-0969-4122-a76a-dca99555d6f0)
![결과페이지2](https://github.com/depromeet/na-lab-client/assets/26461307/459be0a9-1ba9-4d75-9a33-b36ed1747db8)
> 많은 사람들의 답변 속에서 정말 나에게 도움이 되는 피드백은 어느 것일까요?   
> 나랩은 유저가 개별 답변에 대한 이해도를 높이며 의미 있는 피드백을 얻을 수 있도록 결과를 정리했어요

<br />

<br />


![커리어명함1](https://github.com/oyeon-kwon/personal_color/assets/61301574/35ce40d7-c57c-4f61-80c9-6f7d643f0fe1)
![커리어명함2](https://github.com/oyeon-kwon/personal_color/assets/61301574/35ce40d7-c57c-4f61-80c9-6f7d643f0fe1)
> 피드백 결과를 통해 나의 커리어 연구 결과를 확인할 수 있고,   
> 동료들의 피드백을 저장해 나만의 커리어 명함을 만들 수 있어요


<br />

<br />

![추가이미지1](https://github.com/depromeet/na-lab-client/assets/26461307/53c6a91b-d029-4fd9-acad-647a771507e3)

![추가이미지2](https://github.com/depromeet/na-lab-client/assets/26461307/27586832-3bd7-4cbb-a659-1e446ed996d3)

</br>

---

## 😎 Develoment Description

- 안정성과 유지보수를 위해서 단위테스트, 통합테스트, E2E 테스트를 모두 짜는 전략으로 진행
- 테스트 커버리는 분기와 라인 커버리지를 모두 검증하였으며 ***테스트 커버리지 93.7%를 달성***
- 특히, E2E 테스트를 통해 실제 사용자의 여러 시나리오를 테스트함으로써 애플리케이션의 무결성을 검증하고자 하였으며 도입 이후 2차 MVP의 QA 에서 ***버그 제로 달성***
- 유연하고 확장가능한 서비스를 위해 멀티모듈과 헥사고날 아키텍처를 적용
- E2E 부터 깃허브 라벨링, PR 알람 등의 가능한 모든 작업을 자동화시켜 팀의 생산성 증대

</br>

## 🏛️ System Architecture

![아키텍처이미지](https://github.com/oyeon-kwon/personal_color/assets/61301574/794d7625-f63f-418f-b03a-a7ab396f015b)

</br>

## 📚 Tech Stack

<div align="left">
<div>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white">
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white">
</div>

<div>
<img src="https://img.shields.io/badge/MySQL-4479A1.svg?style=flat-square&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white">
</div>

<div>
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white">
<img src="https://img.shields.io/badge/JSON Web Tokens-000000?style=flat-square&logo=JSON Web Tokens&logoColor=white">
</div>

<div>
<img src="https://img.shields.io/badge/SonarCloud-F3702A?style=flat-square&logo=SonarCloud&logoColor=white">
<img alt="HURL" src="https://img.shields.io/badge/-HURL-black?style=for-the-badge&logo=data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAANIAAACqCAYAAADY6BHuAAAMPmlDQ1BJQ0MgUHJvZmlsZQAASImVVwdYU8kWnluSkEBoAQSkhN4EkRpASggt9I5gIyQBQokxEFTsyKKCa0HFAjZ0VUTBCogdsbMo9r4goqKsiwW78iYFdN1Xvne%2Bb%2B797z9n%2FnPm3LllAFA7yRGJclB1AHKF%2BeLYYH%2F6uOQUOukpQAAKqEAZGHO4eSJmdHQ4gDZ0%2Fru9uwm9oV2zl2r9s%2F%2B%2FmgaPn8cFAImGOI2Xx82F%2BCAAeBVXJM4HgCjlzabli6QYNqAlhglCvEiKM%2BS4SorT5HivzCc%2BlgVxKwBKKhyOOAMA1SuQpxdwM6CGaj%2FEjkKeQAiAGh1in9zcKTyIUyG2hj4iiKX6jLQfdDL%2Bppk2rMnhZAxj%2BVxkphQgyBPlcGb8n%2BX435abIxmKYQmbSqY4JFY6Z1i329lTwqRYBeI%2BYVpkFMSaEH8Q8GT%2BEKOUTElIgtwfNeDmsWDNgA7EjjxOQBjEBhAHCXMiwxV8WrogiA0xXCHodEE%2BOx5iXYgX8fMC4xQ%2Bm8VTYhWx0IZ0MYup4M9zxLK40lgPJdkJTIX%2B60w%2BW6GPqRZmxidBTIHYvECQGAmxKsQOedlxYQqfsYWZrMghH7EkVpq%2FOcSxfGGwv1wfK0gXB8Uq%2FEtz84bmi23OFLAjFXh%2FfmZ8iLw%2BWCuXI8sfzgW7whcyE4Z0%2BHnjwofmwuMHBMrnjj3jCxPiFDofRPn%2BsfKxOEWUE63wx035OcFS3hRil7yCOMVYPDEfLki5Pp4uyo%2BOl%2BeJF2ZxQqPl%2BeDLQThggQBABxLY0sAUkAUE7X2NffBK3hMEOEAMMgAf2CuYoRFJsh4hPMaBQvAnRHyQNzzOX9bLBwWQ%2FzrMyo%2F2IF3WWyAbkQ2eQJwLwkAOvJbIRgmHoyWCx5AR%2FCM6BzYuzDcHNmn%2Fv%2BeH2O8MEzLhCkYyFJGuNuRJDCQGEEOIQUQbXB%2F3wb3wcHj0g80JZ%2BAeQ%2FP47k94QuggPCLcIHQS7kwWFIl%2FyjICdEL9IEUt0n6sBW4JNV1xf9wbqkNlXAfXB%2Fa4C4zDxH1hZFfIshR5S6tC%2F0n7bzP44W4o%2FMiOZJQ8guxHtv55pKqtquuwirTWP9ZHnmvacL1Zwz0%2Fx2f9UH0ePIf97Iktwg5g57BT2AXsKNYI6NgJrAlrw45J8fDqeixbXUPRYmX5ZEMdwT%2FiDd1ZaSXzHGsdex2%2FyPvy%2BdOl72jAmiKaIRZkZObTmfCLwKezhVyHUXQnRydnAKTfF%2Fnr602M7LuB6LR95xb8AYD3icHBwSPfudATAOxzh4%2F%2F4e%2BcNQN%2BOpQBOH%2BYKxEXyDlceiDAt4QafNL0gBEwA9ZwPk7ADXgBPxAIQkEUiAfJYBLMPhOuczGYBmaB%2BaAElIHlYDVYDzaBrWAn2AP2g0ZwFJwCZ8ElcAXcAPfg6ukBL0A%2FeAc%2BIwhCQqgIDdFDjBELxA5xQhiIDxKIhCOxSDKSimQgQkSCzEIWIGVIObIe2YLUIPuQw8gp5ALSgdxBupBe5DXyCcVQFVQLNUQt0dEoA2WiYWg8OhHNQKeihWgxuhRdi1aju9EG9BR6Cb2BdqIv0AEMYMqYDmaC2WMMjIVFYSlYOibG5mClWAVWjdVhzfA%2BX8M6sT7sI07EaTgdt4crOARPwLn4VHwOvgRfj%2B%2FEG%2FBW%2FBrehffj3whUggHBjuBJYBPGETII0wglhArCdsIhwhn4LPUQ3hGJRB2iFdEdPovJxCziTOIS4gZiPfEksYPYTRwgkUh6JDuSNymKxCHlk0pI60i7SSdIV0k9pA9KykrGSk5KQUopSkKlIqUKpV1Kx5WuKj1V%2BkxWJ1uQPclRZB55BnkZeRu5mXyZ3EP%2BTNGgWFG8KfGULMp8ylpKHeUM5T7ljbKysqmyh3KMskB5nvJa5b3K55W7lD%2BqaKrYqrBUJqhIVJaq7FA5qXJH5Q2VSrWk%2BlFTqPnUpdQa6mnqQ%2BoHVZqqgypblac6V7VStUH1qupLNbKahRpTbZJaoVqF2gG1y2p96mR1S3WWOkd9jnql%2BmH1W%2BoDGjSNMRpRGrkaSzR2aVzQeKZJ0rTUDNTkaRZrbtU8rdlNw2hmNBaNS1tA20Y7Q%2BvRImpZabG1srTKtPZotWv1a2tqu2gnak%2FXrtQ%2Bpt2pg%2BlY6rB1cnSW6ezXuanzaYThCOYI%2FojFI%2BpGXB3xXnekrp8uX7dUt173hu4nPbpeoF623gq9Rr0H%2Bri%2BrX6M%2FjT9jfpn9PtGao30GskdWTpy%2F8i7BqiBrUGswUyDrQZtBgOGRobBhiLDdYanDfuMdIz8jLKMVhkdN%2Bo1phn7GAuMVxmfMH5O16Yz6Tn0tfRWer%2BJgUmIicRki0m7yWdTK9ME0yLTetMHZhQzhlm62SqzFrN%2Bc2PzCPNZ5rXmdy3IFgyLTIs1Fucs3ltaWSZZLrRstHxmpWvFtiq0qrW6b0219rWeal1tfd2GaMOwybbZYHPFFrV1tc20rbS9bIfaudkJ7DbYdYwijPIYJRxVPeqWvYo9077Avta%2By0HHIdyhyKHR4eVo89Epo1eMPjf6m6OrY47jNsd7YzTHhI4pGtM85rWTrRPXqdLpujPVOch5rnOT8ysXOxe%2By0aX26401wjXha4trl%2Fd3N3EbnVuve7m7qnuVe63GFqMaMYSxnkPgoe%2Fx1yPox4fPd088z33e%2F7lZe%2BV7bXL69lYq7H8sdvGdnubenO8t3h3%2BtB9Un02%2B3T6mvhyfKt9H%2FmZ%2BfH8tvs9Zdows5i7mS%2F9Hf3F%2Fof837M8WbNZJwOwgOCA0oD2QM3AhMD1gQ%2BDTIMygmqD%2BoNdg2cGnwwhhISFrAi5xTZkc9k17P5Q99DZoa1hKmFxYevDHoXbhovDmyPQiNCIlRH3Iy0ihZGNUSCKHbUy6kG0VfTU6CMxxJjomMqYJ7FjYmfFnoujxU2O2xX3Lt4%2Ffln8vQTrBElCS6Ja4oTEmsT3SQFJ5Umd40aPmz3uUrJ%2BsiC5KYWUkpiyPWVgfOD41eN7JrhOKJlwc6LVxOkTL0zSn5Qz6dhktcmcyQdSCalJqbtSv3CiONWcgTR2WlVaP5fFXcN9wfPjreL18r355fyn6d7p5enPMrwzVmb0ZvpmVmT2CViC9YJXWSFZm7LeZ0dl78gezEnKqc9Vyk3NPSzUFGYLW6cYTZk%2BpUNkJyoRdU71nLp6ar84TLw9D8mbmNeUrwV%2F5Nsk1pJfJF0FPgWVBR%2BmJU47MF1junB62wzbGYtnPC0MKvxtJj6TO7Nllsms%2BbO6ZjNnb5mDzEmb0zLXbG7x3J55wfN2zqfMz57%2Fe5FjUXnR2wVJC5qLDYvnFXf%2FEvxLbYlqibjk1kKvhZsW4YsEi9oXOy9et%2FhbKa%2F0YpljWUXZlyXcJRd%2FHfPr2l8Hl6YvbV%2FmtmzjcuJy4fKbK3xX7CzXKC8s714ZsbJhFX1V6aq3qyevvlDhUrFpDWWNZE3n2vC1TevM1y1f92V95voblf6V9VUGVYur3m%2Fgbbi60W9j3SbDTWWbPm0WbL69JXhLQ7VldcVW4taCrU%2B2JW479xvjt5rt%2BtvLtn%2FdIdzRuTN2Z2uNe03NLoNdy2rRWklt7%2B4Ju6%2FsCdjTVGdft6Vep75sL9gr2ft8X%2Bq%2Bm%2FvD9rccYByoO2hxsOoQ7VBpA9Iwo6G%2FMbOxsym5qeNw6OGWZq%2FmQ0ccjuw4anK08pj2sWXHKceLjw%2BeKDwxcFJ0su9Uxqnulskt906PO329Naa1%2FUzYmfNng86ePsc8d%2BK89%2FmjFzwvHL7IuNh4ye1SQ5tr26HfXX8%2F1O7W3nDZ%2FXLTFY8rzR1jO45f9b166lrAtbPX2dcv3Yi80XEz4ebtWxNudd7m3X52J%2BfOq7sFdz%2Ffm3efcL%2F0gfqDiocGD6v%2FsPmjvtOt81hXQFfbo7hH97q53S8e5z3%2B0lP8hPqk4qnx05pnTs%2BO9gb1Xnk%2B%2FnnPC9GLz30lf2r8WfXS%2BuXBv%2Fz%2Bausf19%2FzSvxq8PWSN3pvdrx1edsyED3w8F3uu8%2FvSz%2Fofdj5kfHx3KekT08%2FT%2FtC%2BrL2q83X5m9h3%2B4P5g4OijhijuxXAIMNTU8H4PUOAKjJANDg%2FowyXr7%2Fkxki37PKEPhPWL5HlJkbAHXw%2Fz2mD%2F7d3AJg7za4%2FYL6ahMAiKYCEO8BUGfn4Ta0V5PtK6VGhPuAzbFf03LTwL8x%2BZ7zh7x%2FPgOpqgv4%2Bfwv%2Fm18RiUyhHAAAACKZVhJZk1NACoAAAAIAAQBGgAFAAAAAQAAAD4BGwAFAAAAAQAAAEYBKAADAAAAAQACAACHaQAEAAAAAQAAAE4AAAAAAAAAkAAAAAEAAACQAAAAAQADkoYABwAAABIAAAB4oAIABAAAAAEAAADSoAMABAAAAAEAAACqAAAAAEFTQ0lJAAAAU2NyZWVuc2hvdMReJOkAAAAJcEhZcwAAFiUAABYlAUlSJPAAAAHWaVRYdFhNTDpjb20uYWRvYmUueG1wAAAAAAA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJYTVAgQ29yZSA2LjAuMCI%2BCiAgIDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI%2BCiAgICAgIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiCiAgICAgICAgICAgIHhtbG5zOmV4aWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vZXhpZi8xLjAvIj4KICAgICAgICAgPGV4aWY6UGl4ZWxZRGltZW5zaW9uPjE3MDwvZXhpZjpQaXhlbFlEaW1lbnNpb24%2BCiAgICAgICAgIDxleGlmOlBpeGVsWERpbWVuc2lvbj4yMTA8L2V4aWY6UGl4ZWxYRGltZW5zaW9uPgogICAgICAgICA8ZXhpZjpVc2VyQ29tbWVudD5TY3JlZW5zaG90PC9leGlmOlVzZXJDb21tZW50PgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4K99h6PgAAABxpRE9UAAAAAgAAAAAAAABVAAAAKAAAAFUAAABVAAAHsiesp%2FUAAAd%2BSURBVHgB7J1JbBxFFIbfxBbCCXFCIDAWHOHECXGIxIUj3BE3JLIc7CTOQgSJgAgQHNiEOCDCIi6AAMGBxUs2L%2BMsGLIBTkwSsu8JznjGM5595dXEdpAdXNOZqurqnr99sN3V%2FerVV%2F1p7O7qqkBLy4NlwgYCIFATgQBEqokfTgaBCgGIhAsBBBQQgEgKICIECEAkXAMgoIAARFIAESFAACLhGgABBQQgkgKICAECEAnXAAgoIACRFEBECBCASLgGQEABAYikACJCgABEwjUAAgoIQCQFEBECBCASrgEQUEAAIimAiBAgAJFwDSgl0N68hLbE91OJ6us1N4ik9DJCsIHgctqXvUSvjPVSoVyqGyAQqW662kxDQ8FltKhhLu3JnKcXItspUy6YqdjlWiCSyx3gt%2Br7WaR7WCSx%2FZ69Qmsi3ZQo5fzWzBntgUgzkGBHLQT%2BK5KIczx3nVaOdlKklK4lrPXnQiTru8hbCfbxJ9K9E59Ik5mfK4xRW7iDrhbHJ3f57jtE8l2XutugvuBSFmnejCSusUSt4U46V4jOKPPDDojkh160qA3%2FJ5JIMVJM0yr%2BM%2B9Y%2FrpFGatJBSKp4YgoEwR6%2BRNp8S0%2BkSYBJUpZWju6lQ7lrkzu8sV3iOSLbrSnETKRRKbilri4NS5ukftlg0h%2B6UlL2lGNSCJV8bB2c7SXtqVPWpJ5bWlApNr44expBKoVSZxWKpfprdhu%2Bj45PC2K936FSN7rM6szdiLSjYaU6cP4Pvp8%2FJDV7ZIlB5FkhFDuiIBzkW6E%2FyLxB30QG%2FTsUFeI5OgywcEyArcrkoj7Y%2FIovTE24MmR4xBJdmWg3BGBWkQSFfWkT9NL0R7Kl4uO6nX7YIjkdg%2F4rP5aRRI4BjMXaAPfHk%2BX856hA5E801XeSFSFSKKlQ7lr1D7aRXF%2BgOuFDSJ5oZc8lGMPj2y4b5aRDU6aciIfrowcDxdTTk5z5ViI5Ap2%2F1aqUiRB6WIhxoNdO%2BhyMW41NIhkdfd4LznVIgkCI8UEv4bRSacLEWuBQCRru8abiekQSZAYK2VoNY8cH86NWAkGIlnZLd5NStXNhlsRSPIr6%2BsjW2l%2F9vKtil3dB5Fcxe%2B%2FynWKJGhleeT4pshOCmXOWgUPIlnVHd5PRrdIglCRR46%2FNtZPnam%2FrQFmTKQAN%2FmOQKM1DUciegh03%2F%2FsrC%2F2qaq1zCPH343tpW%2BSh1WFrCmOMZGCDXfRjuBzNSWLk0FgOoGPeVbXT8YPTN9t%2FHeIZBw5KlRN4OvEEL0X%2B4VHjrs3TTJEUt2riOcKgY7UcXo9GqIijx13Y4NIblBHnVoIhNJnaGN0J%2BVcGDkOkbR0KYK6RWA%2FT%2BC%2FjmcpShkeOQ6R3Opx1KuNwHDuH54%2Fr4tiPBrC1AaRTJFGPUYJnM5HqG20g8fpJY3UC5GMYEYlbhC4XIhTK8skRpDr3iCSbsKI7yqBMH8itfFg15P5Ua15QCSteBHcBgLxysjxbjrMb93q2iCSLrKIaxWBdClPz0e20a%2FZi1rygkhasCKojQTE86U1o930mwaZIJKNPY6ctBA4yO8xiWdMibL6pTghkpYuQ1DbCOzKnKMXIzsq7zPpyA0i6aCKmFYR6E6doFejfVTQOA4PIlnV5UhGNYHvEkfo7dge7dMgQyTVPYd41hD4bPwgfcQrXZjYIJIJyqjDKAHx9uz78UH6KvGnsXohkjHUqMgEATGfg1jR4qfUMRPVTdUBkaZQ4AevExDPicRKFr28ooXpDSKZJo76tBBITYxc0PGwtZqEIVI1lHCM1QRMjKWTAYBIMkIot5qAqdHdMggQSUYI5dYSuMTvG4mX90y8bySDAJFkhFBuJYFTE2%2FAXjf0BqwMgjGRGmkOPdDYLMsH5R4n8OXip2nhnDu1tuIIz8mw2vCcDLIGGRNJlgjK%2FUEgFFxGixrmamvMPp4laL0LswTJGgSRZIRQ7ohAKLicRWpydE61B%2FfzvHWbXJq3TpYjRJIRQrkjArpEcnsmVRkEiCQjhHJHBHSIZMPc3jIIEElGCOWOCKgWaQuvNvGpBatNyCBAJBkhlDsiMMD%2FI92t4H8kMYL7HV7%2F6FtL1j%2BSQYBIMkIod0RAhUiFiRX5uixakU8GASLJCKHcEYFaRRJrxG7kNWIHLFsjVgYBIskIodwRgVpEEquWr%2BNVyw9YuGq5DAJEkhFCuSMCtyvSGM%2BGuoqnFv4rN%2BKoPlsOhki29IRP8tjFNxsWOrzZMFJMUGu4k84UIp6lAJE823V2Ju5UpAu8UkRr%2BGe6Uhy3s0FVZlV3IrU0zKerHu%2B0KvvWlcN2tayoetDqiXyYVvInUbiUciVXlZXWlUhPNT1MTzY9VJlMXSVExLpJoFqRhnhliHYewR0vZW%2Be7OGf6kakZ%2BY9Qi8veIL2Zs9XJlL3cJ9ZnXo1Ig1mLtCGyHZKG17nVSe4uhBpxfzHaE3zEgrwl5gDei2vSIBND4Hd%2FKfdglneR%2BpJn%2BKZfnop78LK43pafCOqr0UKcBvXNz9OS%2Bc%2FOsVwIH228qxiagd%2BUEpgNpF%2BSB6lN3nOuRKVldZpQ7B%2FAQAA%2F%2F%2FAW6AkAAAH0UlEQVTtnV1sFFUUx89%2BdnfLtrvdCi5CkabKV1AkIAQwhYIVKC2JxgQTSYwaSUARYxONYgjE4Fe0IojlwQRfffUDY1SUitEHxQffNCI%2BEBIjVSpS%2BWg9p3SSttvdndm5Mztz539fpp25986e351fZnfmzplQPj9jmDQsYQrRC5nVdG%2Ft%2FHHRfXnpND15%2FuNx6%2FCPOgIn8o9QfThR0OF7A6eo58I3pOXBxtGGdBQpForQ%2Fuw6ak%2B2FAwoRCpAonTFRJGGWZ2DF76jdwe%2BV7ofr3WmnUjJUJTeaNhAKxJNk7I%2BzmekXTgjTcpGxcqxIg0ND9NLf5%2Bg9y%2F%2BpKJrT%2FehlUjpcA0dynXQoni%2BKHSIVBSNkg19%2FNWujr%2FaXR0eot39n9GxSz8r6dfrnWgjUi6cot7GTro11liS%2BfFLv%2FIZ6VjJOthYOYG%2B%2FKMU56%2FW3ec%2Fob7BM5V35LOWWog0PZKmI41d1BTNlMUPkcoislXh2LSt9Hz%2F5%2FTD5bO2%2BvFbY9%2BL1BzN8pmoi6ZFpphi%2FwWfkZ7CGckUq0oqzebxOH21v5Kmvm7ja5EWxKbS4cZNlAknTQ8CRDKNChUtEPCtSEtrbqIDDRupNhy3EC4RRLKEC5VNEvClSKsTs%2BnVhnaq4UvdVgtEskoM9c0Q8J1Im1JzaG%2BmjaKhsJn4CupApAIkWKGAgK9EeqB2IT1TfxeFQqGKQ4dIFaNDwxIEfCPStvRS2l53Z4lQzG2CSOY4oZY1Ap4XKcSTT7vrV9KDU263FlmR2hCpCBistkXA0yJFKEx7smtoc2qurSDHNoZIY2ngb1UEPCuSTDN5JdtObclmVbGO9AORlOJEZ6MEPClSKhSjN3MbaVnNDOUDBZGUI0WHTMBzIslDYW%2FnNtHC%2BDRHBggiOYI18J16SqQbIrXUm%2BuklljOsYGBSI6hDXTHnhFpRrSOjuQ2kyydLBDJSbrB7dsTIskZSM5EckZyukAkpwkHs%2F%2Bqi3Qb%2FxY6xL%2BJJkuY4cSQnL7ST58O%2FuJE1%2BjTIwT%2BHbpCR%2F855eqnqapIy2tmUg%2FnV0iFY64GjZ3pTeCPaxdp3bmjrgZZNZHW8v2hl%2Fk%2BkdwvQgEBlQQCI9Lm1DzawznnIhXO4FYJHX3pRyAQIm3lOXNP1620NYNbv6FHRCoJaC%2FSjrpl9Fh6iUpm6AsECghoK5LM4H6WnyPaMmVhQdBYAQKqCWgpUpRncO%2FLtlEHP9mKAgJuENBOJMmp8BrnVmjlHAsoIOAWAa1Eqg3F6S2ewb2Es%2F2ggICbBLQRKct55g7zbIX58alu8sO%2BQGCEgBYiScbT3lwXNceyGFYQqAoBLUSSy9tymRsFBKpFQAuRBJ5k%2B5GsPyggUA0C2ogk8CTrTzdmMFTjOAr8PrUSSUZTsv%2FsyazBnLrAH9ruAtBOJMG3NsGzvPleEmZ5u3swBXlvWookA7qcswH18Jsj8NxRkA9v92LXViRBKFmBJDuQW0%2FCujds2JPXCGgtksBuiTWM3GNyIzeD1wYXn8c9AtqLJCivZwvq4mW9e2Sxp0ARCIRIMqJu5K8L1JGDYMcRCIxIErXTGVXHkcU%2FgSIQKJFkZCXHd09uA1%2FVmxmogUawzhIInEiCU%2B4vSTYhySqEAgIqCARSJAEX4UfRr78HaZ4KjiX7EMg%2FXj5Xsg42%2BpvAwNB%2FtPev464GUbW8dhOjlLfCdtevUvZmvon9G%2F9%2FNfgb7fzzI%2BNfLEFACQHPiGREs40fw9ju4GMYEMkgjaVKAp4TSYLbMvr28rCNt5cXg3SCz0hP4IxUDA%2FWV0jAkyJJLJJ1aF%2BmjaKKs7FCpAqPFDQrScCzIsmnbk3czFmI7iHJRqSqQCRVJNHPWAKeFkk%2B6FLOQnSAZ47XhuNjP3fFf0OkitGhYQkCnhdJPvuC2NSRmePZSLJEKOY2QSRznFDLGgFfiCQhNUez1NvYRZKlyE6BSHbooW0xAr4RSQKYHknTEZapKZopFk%2FZ9X181e5xXLUrywkVrBHwlUgSWi6concaO2lOrNFapKO1IVJF2NCoDAHfiSTxpMM1dKihgxbV5MuEV7i5b%2FAMn5E%2BLNyANSBgg4AvRZJ4k3xJ%2FHV%2B%2F%2BzKRJOl8CGSJVyobJKAb0WS%2BGJ8s3Z%2F9m5qT7aYDJcIIplGhYoWCPhaJIkzzDPHd2da6b7aBabChkimMKGSRQK%2BF0nilZnju%2BpW0EPpO8qG%2FzX%2FRtqB30hlOaGCNQJaiGSE%2FHB6Me2sW85iiVqTF4g0ORestUdAK5EExf38Fe%2B5%2BlYqNnMcItk7YNB6cgLaiSRhrk%2FeQi9m1%2FLFiEhB1BCpAAlWKCCgpUjCZVViFl8eX0%2BJCTPHIZKCowZdFBDQViSJdHE8z%2B%2Bx7Ri5gWtEfpIvNmzHxQYDB5aKCGgtkjCay1OJ3sl1UkMkNYLs5ODvLNIHivChGxC4TkB7kSTMWTzJ9Qi%2F1zYfTRNEwqHvBIFAiCTgbhx9SfTZawM4IzlxJAW8z8CIJOOcDSdH0n0dvPBtwIcd4asmECiRVMNDfyBgEIBIBgksQcAGAYhkAx6agoBBACIZJLAEARsEIJINeGgKAgYBiGSQwBIEbBCASDbgoSkIGAQgkkECSxCwQQAi2YCHpiBgEIBIBgksQcAGAYhkAx6agoBBACIZJLAEARsEIJINeGgKAgYBiGSQwBIEbBD4H%2FXbLRgsnfbmAAAAAElFTkSuQmCC&logoColor=black">
</div>

<div>
<img src="https://img.shields.io/badge/Slack-4A154B?style=flat-square&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=notion&logoColor=white">
</div>


</div>

<br/>

## 🧑🏻‍💻 Developers

<table>
    <tr align="center">
        <td><B>Backend</B></td>
        <td><B>Backend</B></td>
        <td><B>Backend</B></td>
    </tr>
    <tr align="center">
        <td><B>이준영</B></td>
        <td><B>이수진</B></td>
        <td><B>유도진</B></td>
    </tr>
    <tr align="center">
        <td>
            <img src="https://github.com/devxb.png?size=100">
            <br>
            <a href="https://github.com/devxb"><I>devxb</I></a>
        </td>
        <td>
            <img src="https://github.com/ssssujini99.png?size=100" width="100">
            <br>
            <a href="https://github.com/ssssujini99"><I>ssssujini99</I></a>
        </td>
        <td>
            <img src="https://github.com/dojinyou.png?size=100" width="100">
            <br>
            <a href="https://github.com/dojinyou"><I>dojinyou</I></a>
        </td>
    </tr>
    <tr align="center">
        <td>
            <img src="https://github.com/depromeet/na-lab-server/assets/71487608/b63d382b-a879-483b-9efb-bb6652811911" width="100" height="75">
            <br>
            <a href="https://www.nalab.me/dna/459013095722156718"><I>devxb의 커리어 명함</I></a>
        </td>
        <td>
            <img src="https://github.com/depromeet/na-lab-server/assets/71487608/263d7e89-4d4b-45d0-b595-528beecaf730" width="100" height="75">
            <br>
            <a href="https://www.nalab.me/dna/468088240919167918"><I>ssssujini99의 커리어 명함</I></a>
        </td>
        <td>
            <img src="https://github.com/depromeet/na-lab-server/assets/71487608/b63d382b-a879-483b-9efb-bb6652811911" width="100" height="75">
            <br>
            <a href="https://www.nalab.me/dna/467660084072569641"><I>dojinyou의 커리어 명함</I></a>
        </td>
    </tr>
</table>


</br>


![추가이미지3](https://github.com/depromeet/na-lab-server/assets/71487608/09a06bb1-4f06-4513-977d-e6fe49bd8f06)
