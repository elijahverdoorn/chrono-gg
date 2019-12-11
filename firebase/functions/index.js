'use strict';

const functions = require('firebase-functions');
const fetch = require('node-fetch');
const {
	WebhookClient
} = require('dialogflow-fulfillment');
const {
	Card,
	Suggestion,
	Button
} = require('dialogflow-fulfillment');
const {
	RegistrationUpdate
} = require('actions-on-google');

process.env.DEBUG = 'dialogflow:debug'; // enables lib debugging statements

// URLs
const DEAL_JSON_URL = "https://elijahverdoorn.com/chronogg/deal.json";

// Phrases (exposed to users)
const ERROR_TEXT = "Sorry, there's been an error. Please try again later.";

// Intent names
const GET_DAILY_DEAL_INTENT = "GetDailyDeal";
const GET_DAILY_DEAL_ROUTINE_INTENT = "GetDailyDealRoutineUpdate";

exports.dialogflowFirebaseFulfillment = functions.https.onRequest((request, response) => {
  const agent = new WebhookClient({ request, response });

  async function getDailyDeal(agent) {
		let json =  await getDealJSON();
    let spokenText = `Today's deal is ${json.title}, it costs $${json.price}.`;
    let cardText = `${spokenText} That's a ${json.discountPercent}% discount; it costs $${json.steamPrice} on Steam otherwise.`;
    let sTitle = `$${json.price}`;
    let buttonText = `Open Chrono.gg`;

    agent.add(spokenText);
    agent.add(new Card({
      	title: json.title,
      	subtitle: sTitle,
        imageUrl: json.imgUrl,
        text: cardText,
        buttonText: buttonText,
        buttonUrl: json.link
		}));
	}

  let getDealJSON = () => {
		return fetch(DEAL_JSON_URL).then(res => res.json());
  };

  // Run the proper function handler based on the matched Dialogflow intent name
  let intentMap = new Map();
  intentMap.set(GET_DAILY_DEAL_INTENT, getDailyDeal);
  intentMap.set(GET_DAILY_DEAL_ROUTINE_INTENT, getDailyDeal); // implicit invocation for daily update flow

  agent.handleRequest(intentMap);
});

