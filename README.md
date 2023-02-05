<h2 align="center">Image generator using DALL-E artificial intelligence with Google Translate</h2>

###

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="40" width="52" alt="spring logo"  />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" width="52" alt="java logo"  />
</div>

###

<h4 align="left">The sentence you enter in any language is translated into English with Google Translate. Then, with this translation, DALL-E artificial intelligence creates as many pictures as the number entered on the screen for you.</h4>

###

<p align="left">Prompt: required<br>Piece: not requeired -> default: 1<br>Size: not required -> default: 1024x1024</p>

###

<div align="left">
  <img height="1024" src="https://github.com/ilyasaglar/DALL-E-image-creator-with-Google-Translate/blob/main/images/1024x1024.png"  />
</div>

###

<div align="center">
  <img height="512" src="https://github.com/ilyasaglar/DALL-E-image-creator-with-Google-Translate/blob/main/images/512.png"  />
</div>

###

<h3 align="left">DALL-E</h3>

###

<p align="left">Go to https://openai.com/api/login<br>Create a OpenAI Account<br>Go to https://platform.openai.com/account/api-keys<br>Create new API key<br><br>DALL-E API Documentation: https://platform.openai.com/docs/api-reference/images<br><br>Example Request:</p>

###

<p align="left">curl https://api.openai.com/v1/images/generations \<br>  -H 'Content-Type: application/json' \<br>  -H 'Authorization: Bearer YOUR_API_KEY' \<br>  -d '{<br>  "prompt": "A cute baby sea otter",<br>  "n": 2,<br>  "size": "1024x1024"<br>}'</p>

###

<p align="left">------------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>

###

<h3 align="left">Google Translate</h3>

###

<h4 align="left">Go to https://console.cloud.google.com/apis/dashboard<br>Create a Google Account<br>Create a new Project<br>Go to https://console.cloud.google.com/apis/credentials<br>Create a new API Key</h4>

###

<br clear="both">

<p align="left">Use to Google Translate Library Dependency<br><br><dependency><br>			 <groupId>com.google.cloud</groupId><br>			<artifactId>google-cloud-translate</artifactId><br>			<version>2.9.0</version><br>		</dependency></p>

###
